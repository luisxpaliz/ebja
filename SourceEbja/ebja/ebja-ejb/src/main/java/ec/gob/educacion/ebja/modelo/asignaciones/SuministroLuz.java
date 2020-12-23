package ec.gob.educacion.ebja.modelo.asignaciones;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SUMINISTRO_LUZ", schema="ASIGNACIONES")
public class SuministroLuz implements Serializable {
	private static final long serialVersionUID = 4823746179634820160L;
	
	private String nummedidor;
	private String codigounic;
	private String idccedruc;
	private String nombre;
	private String calles;
	private String dpaParroq;
	private String dpaDespar;
	private String dpaCanton;
	private String dpaDescan;
	private String dpaProvin;
	private String dpaDespro;
	private String zona;
	private String desCirc;
	private String codPostal;
	private String codDistri;
	private String codCircui;
	private String CX;
	private String CY;

	public SuministroLuz() {
	}

	public SuministroLuz(String nummedidor, String codigounic,
			String idccedruc, String nombre, String calles, String dpaParroq,
			String dpaDespar, String dpaCanton, String dpaDescan,
			String dpaProvin, String dpaDespro, String zona, String desCirc,
			String codPostal, String codDistri, String codCircui, String CX,
			String CY) {
		this.nummedidor = nummedidor;
		this.codigounic = codigounic;
		this.idccedruc = idccedruc;
		this.nombre = nombre;
		this.calles = calles;
		this.dpaParroq = dpaParroq;
		this.dpaDespar = dpaDespar;
		this.dpaCanton = dpaCanton;
		this.dpaDescan = dpaDescan;
		this.dpaProvin = dpaProvin;
		this.dpaDespro = dpaDespro;
		this.zona = zona;
		this.desCirc = desCirc;
		this.codPostal = codPostal;
		this.codDistri = codDistri;
		this.codCircui = codCircui;
		this.CX = CX;
		this.CY = CY;
	}

	@Column(name = "NUMMEDIDOR")
	public String getNummedidor() {
		return this.nummedidor;
	}

	public void setNummedidor(String nummedidor) {
		this.nummedidor = nummedidor;
	}

	@Id
	@Column(name = "CODIGOUNIC")
	public String getCodigounic() {
		return this.codigounic;
	}

	public void setCodigounic(String codigounic) {
		this.codigounic = codigounic;
	}

	@Column(name = "IDCCEDRUC")
	public String getIdccedruc() {
		return this.idccedruc;
	}

	public void setIdccedruc(String idccedruc) {
		this.idccedruc = idccedruc;
	}

	@Column(name = "NOMBRE")
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "CALLES")
	public String getCalles() {
		return this.calles;
	}

	public void setCalles(String calles) {
		this.calles = calles;
	}

	@Column(name = "DPA_PARROQ")
	public String getDpaParroq() {
		return this.dpaParroq;
	}

	public void setDpaParroq(String dpaParroq) {
		this.dpaParroq = dpaParroq;
	}

	@Column(name = "DPA_DESPAR")
	public String getDpaDespar() {
		return this.dpaDespar;
	}

	public void setDpaDespar(String dpaDespar) {
		this.dpaDespar = dpaDespar;
	}

	@Column(name = "DPA_CANTON")
	public String getDpaCanton() {
		return this.dpaCanton;
	}

	public void setDpaCanton(String dpaCanton) {
		this.dpaCanton = dpaCanton;
	}

	@Column(name = "DPA_DESCAN")
	public String getDpaDescan() {
		return this.dpaDescan;
	}

	public void setDpaDescan(String dpaDescan) {
		this.dpaDescan = dpaDescan;
	}

	@Column(name = "DPA_PROVIN")
	public String getDpaProvin() {
		return this.dpaProvin;
	}

	public void setDpaProvin(String dpaProvin) {
		this.dpaProvin = dpaProvin;
	}

	@Column(name = "DPA_DESPRO")
	public String getDpaDespro() {
		return this.dpaDespro;
	}

	public void setDpaDespro(String dpaDespro) {
		this.dpaDespro = dpaDespro;
	}

	@Column(name = "ZONA")
	public String getZona() {
		return this.zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}

	@Column(name = "DES_CIRC")
	public String getDesCirc() {
		return this.desCirc;
	}

	public void setDesCirc(String desCirc) {
		this.desCirc = desCirc;
	}

	@Column(name = "COD_POSTAL")
	public String getCodPostal() {
		return this.codPostal;
	}

	public void setCodPostal(String codPostal) {
		this.codPostal = codPostal;
	}

	@Column(name = "COD_DISTRI")
	public String getCodDistri() {
		return this.codDistri;
	}

	public void setCodDistri(String codDistri) {
		this.codDistri = codDistri;
	}

	@Column(name = "COD_CIRCUI")
	public String getCodCircui() {
		return this.codCircui;
	}

	public void setCodCircui(String codCircui) {
		this.codCircui = codCircui;
	}

	@Column(name = "C_X")
	public String getCX() {
		return this.CX;
	}

	public void setCX(String CX) {
		this.CX = CX;
	}

	@Column(name = "C_Y")
	public String getCY() {
		return this.CY;
	}

	public void setCY(String CY) {
		this.CY = CY;
	}
}