package ec.gob.educacion.ebja.recursos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

import ec.gob.educacion.ebja.controlador.BaseControlador;
import ec.gob.educacion.ebja.controlador.SesionControlador;
import ec.gob.educacion.ebja.dto.ItemMenuDTO;
import ec.gob.educacion.ebja.dto.SubMenuDTO;
import ec.gob.educacion.ebja.modelo.seguridades.Recurso;

@Named
@RequestScoped
public class MenuBean extends BaseControlador implements Serializable {

	private static final long serialVersionUID = -3170588724362450437L;
	@Inject
	private SesionControlador sesionControlador;

	private MenuModel menuModelVertical;
	private MenuModel menuModelHorizontal;
	private List<Recurso> recursos;

	public MenuBean() {
	}

	@PostConstruct
	public void init() {
		if (sesionControlador.getUsuarioSesion() != null) {
			menuModelHorizontal = sesionControlador.getMenuModelHorizontal();
			recursos = sesionControlador.getRecursos();
			if (recursos != null && !recursos.isEmpty()) {
				menuModelHorizontal = new DefaultMenuModel();
				generateMenu(menuModelHorizontal, false);
				sesionControlador.setMenuModelHorizontal(menuModelHorizontal);
			}
		} else {
			redireccionarPagina("/faces/login.xhtml");
		}
	}

	public void generateMenu(MenuModel menuModel, boolean esVertical) {
		List<SubMenuDTO> menuGuardado = generarMenu();
		for (SubMenuDTO subMenuDTO : menuGuardado) {
			generarSubMenu(menuModel, subMenuDTO, esVertical);
		}
	}

	public void generarSubMenu(MenuModel menuModel, SubMenuDTO subMenuDTO, boolean esVertical) {
		DefaultSubMenu submenu = new DefaultSubMenu(
				((subMenuDTO.getValor() != null && !subMenuDTO.getValor().isEmpty()) ? subMenuDTO.getValor()
						: Constantes.NOMBRE_DEFECTO_SUB_MENU));
		if (subMenuDTO.getItems() != null) {
			for (ItemMenuDTO itemMenuDTO : subMenuDTO.getItems()) {
				submenu.addElement(generarMenuItem(itemMenuDTO, esVertical));
			}
			if (esVertical) {
				submenu.setStyleClass(Constantes.ESTILO_DEFECTO_SUB_MENU_VERTICAL);
			}
			menuModel.addElement(submenu);
		} else {
			menuModel.addElement(generarMenuItem(subMenuDTO, esVertical));
		}
	}

	public DefaultMenuItem generarMenuItem(ItemMenuDTO item, boolean esVertical) {
		DefaultMenuItem itemMenu = new DefaultMenuItem(
				((item.getValor() != null && !item.getValor().isEmpty()) ? item.getValor()
						: Constantes.NOMBRE_DEFECTO_ITEM_MENU),
				((item.getIcono() != null && !item.getIcono().isEmpty()) ? item.getIcono()
						: Constantes.ICONO_DEFECTO_MENU),
				((item.getUrl() != null && !item.getUrl().isEmpty()) ? item.getUrl() : Constantes.PAGINA_DEFECTO_MENU));
		if (esVertical) {
			itemMenu.setStyleClass(Constantes.ESTILO_DEFECTO_ITEM_MENU_VERTICAL);
		}
		return itemMenu;
	}

	public DefaultMenuItem generarMenuItem(SubMenuDTO item, boolean esVertical) {
		DefaultMenuItem itemMenu = new DefaultMenuItem(
				((item.getValor() != null && !item.getValor().isEmpty()) ? item.getValor()
						: Constantes.NOMBRE_DEFECTO_ITEM_MENU),
				((item.getIcono() != null && !item.getIcono().isEmpty()) ? item.getIcono()
						: Constantes.ICONO_DEFECTO_MENU),
				((item.getUrl() != null && !item.getUrl().isEmpty()) ? item.getUrl() : Constantes.PAGINA_DEFECTO_MENU));
		if (esVertical) {
			itemMenu.setStyleClass(Constantes.ESTILO_DEFECTO_ITEM_MENU_VERTICAL);
		}
		return itemMenu;
	}

	public List<SubMenuDTO> generarMenu() {
		List<SubMenuDTO> menu = new ArrayList<SubMenuDTO>();
		List<ItemMenuDTO> items = new ArrayList<ItemMenuDTO>();

		for (Recurso recurso : recursos) {
			// Agregamos la página principal
			ItemMenuDTO itemPagPrincipal = new ItemMenuDTO("Inicio", recurso.getUrl() + "/principal.xhtml",
					"ui-icon-home", 0);
			items.add(itemPagPrincipal);
			if (!recurso.getRecursosHijos().isEmpty()) {
				for (Recurso hijo : recurso.getRecursosHijos()) {
					try {
						ItemMenuDTO item = new ItemMenuDTO(capitalize(StringUtils.lowerCase(hijo.getNombre())),
								recurso.getUrl() + hijo.getUrl(), "ui-icon-stop", 0);

						items.add(item);

					} catch (Exception e) {

						e.printStackTrace();
					}
				}
			}
			Collections.sort(items, new ordenarMenu());
			SubMenuDTO submenu = new SubMenuDTO(StringUtils.capitalize(StringUtils.lowerCase(recurso.getNombre())),
					items);
			menu.add(submenu);
		}
		return menu;
	}

	//
	// getters and setters
	//
	private String capitalize(final String line) {
		return Character.toUpperCase(line.charAt(4)) + line.substring(5);
	}

	public MenuModel getMenuModelVertical() {
		return menuModelVertical;
	}

	public void setMenuModelVertical(MenuModel menuModelVertical) {
		this.menuModelVertical = menuModelVertical;
	}

	public List<Recurso> getRecursos() {
		return recursos;
	}

	public void setRecursos(List<Recurso> recursos) {
		this.recursos = recursos;
	}

	public MenuModel getMenuModelHorizontal() {
		return menuModelHorizontal;
	}

	public void setMenuModelHorizontal(MenuModel menuModelHorizontal) {
		this.menuModelHorizontal = menuModelHorizontal;
	}
}

class ordenarMenu implements Comparator<ItemMenuDTO> {
	@Override
	public int compare(ItemMenuDTO o1, ItemMenuDTO o2) {
		return o1.getOrden() - o2.getOrden();
	}
}
