package pe.edu.utp.farmacia.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "venta")
public class SaleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idventa;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    
    @ManyToOne
    @JoinColumn(name = "idcliente")
    private ClientEntity cliente;
    
    @ManyToOne
    @JoinColumn(name = "idempleado")
    private EmployeeEntity empleado;
    
    @ManyToOne
    @JoinColumn(name = "idtipocomprobante")
    private VouchertypeEntity tipoComprobante;
    
    private BigDecimal subtotal;
    private BigDecimal descuento;
    private BigDecimal igv;
    private BigDecimal total;
    private String estado;
    
    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, orphanRemoval = true)
@com.fasterxml.jackson.annotation.JsonManagedReference
private List<SaleDetailEntity> detalles = new ArrayList<>();

    // Constructores
    public SaleEntity() {
        // Inicialización por defecto
        this.fecha = new Date();
        this.estado = "ACTIVO";
        this.descuento = BigDecimal.ZERO;
    }

    // Getters y Setters
    public Integer getIdventa() {
        return idventa;
    }

    public void setIdventa(Integer idventa) {
        this.idventa = idventa;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public ClientEntity getCliente() {
        return cliente;
    }

    public void setCliente(ClientEntity cliente) {
        this.cliente = cliente;
    }

    public EmployeeEntity getEmpleado() {
        return empleado;
    }

    public void setEmpleado(EmployeeEntity empleado) {
        this.empleado = empleado;
    }

    public VouchertypeEntity getTipoComprobante() {
        return tipoComprobante;
    }

    public void setTipoComprobante(VouchertypeEntity tipoComprobante) {
        this.tipoComprobante = tipoComprobante;
    }

    public BigDecimal getSubtotal() {
        return subtotal != null ? subtotal : BigDecimal.ZERO;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getDescuento() {
        return descuento != null ? descuento : BigDecimal.ZERO;
    }

    public void setDescuento(BigDecimal descuento) {
        this.descuento = descuento;
    }

    public BigDecimal getIgv() {
        return igv != null ? igv : BigDecimal.ZERO;
    }

    public void setIgv(BigDecimal igv) {
        this.igv = igv;
    }

    public BigDecimal getTotal() {
        return total != null ? total : BigDecimal.ZERO;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<SaleDetailEntity> getDetalles() {
        if (detalles == null) {
            detalles = new ArrayList<>();
        }
        return detalles;
    }

    public void setDetalles(List<SaleDetailEntity> detalles) {
        this.detalles = detalles;
    }
    
    // Métodos de conveniencia
    public void addDetalle(SaleDetailEntity detalle) {
        detalle.setVenta(this);
        this.detalles.add(detalle);
    }
    
    public void calcularTotales() {
        BigDecimal sub = BigDecimal.ZERO;
        
        for (SaleDetailEntity detalle : this.getDetalles()) {
            sub = sub.add(detalle.getSubtotal());
        }
        
        this.setSubtotal(sub);
        BigDecimal baseImponible = sub.subtract(this.getDescuento());
        this.setIgv(baseImponible.multiply(new BigDecimal("0.18")));
        this.setTotal(baseImponible.add(this.getIgv()));
    }

    @Override
    public String toString() {
        return "Venta [id=" + idventa + ", fecha=" + fecha + ", total=" + total + "]";
    }
}