package br.com.fiap.asseguravel.model;

import br.com.fiap.pessoa.model.Pessoa;
import jakarta.persistence.*;

@Entity
@Table(
        name = "TB_VEICULO",
        uniqueConstraints = {
                @UniqueConstraint(name = "UK_CHASSIS", columnNames = "NR_CHASSIS"),
                @UniqueConstraint(name = "UK_PLACA", columnNames = "NR_PLACA")
        }
)

public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_VEICULO")
    @SequenceGenerator(
            name = "SQ_VEICULO",
            sequenceName = "SQ_VEICULO",
            initialValue = 1,
            allocationSize = 1
    )
    @Column(name = "ID_VEICULO")
    private Long id;

    @Column(name = "NR_CHASSIS")
    private String chassis;

    @Column(name = "NR_PLACA")
    private String placa;

    @Column(name = "DS_MODELO")
    private String modelo;
    @Column(name = "ANO_FABRICACAO")
    private int anoDeFabricacao;

    @Column(name = "NM_FABRICANTE")
    private String fabricante;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "ID_PROPRIETARIO",
            referencedColumnName = "ID_PESSOA",
            foreignKey = @ForeignKey(name = "FK_VEICULO_PROPRIETARIO")
    )
    private Pessoa proprietario;

    public Veiculo() {
    }

    public Veiculo(Long id, String chassis, String placa, String modelo, int anoDeFabricacao, String fabricante, Pessoa proprietario) {
        this.id = id;
        this.chassis = chassis;
        this.placa = placa;
        this.modelo = modelo;
        this.anoDeFabricacao = anoDeFabricacao;
        this.fabricante = fabricante;
        this.proprietario = proprietario;
    }

    public String getChassis() {
        return chassis;
    }

    public Veiculo setChassis(String chassis) {
        this.chassis = chassis;
        return this;
    }

    public String getPlaca() {
        return placa;
    }

    public Veiculo setPlaca(String placa) {
        this.placa = placa;
        return this;
    }

    public String getModelo() {
        return modelo;
    }

    public Veiculo setModelo(String modelo) {
        this.modelo = modelo;
        return this;
    }

    public int getAnoDeFabricacao() {
        return anoDeFabricacao;
    }

    public Veiculo setAnoDeFabricacao(int anoDeFabricacao) {
        this.anoDeFabricacao = anoDeFabricacao;
        return this;
    }

    public String getFabricante() {
        return fabricante;
    }

    public Veiculo setFabricante(String fabricante) {
        this.fabricante = fabricante;
        return this;
    }


    public Pessoa getProprietario() {
        return proprietario;
    }

    public Veiculo setProprietario(Pessoa proprietario) {
        this.proprietario = proprietario;
        return this;
    }

    public Long getId() {
        return id;
    }

    public Veiculo setId(Long id) {
        this.id = id;
        return this;
    }

    @Override
    public String toString() {
        return "Veiculo{" +
                "id=" + id +
                ", chassis='" + chassis + '\'' +
                ", placa='" + placa + '\'' +
                ", modelo='" + modelo + '\'' +
                ", anoDeFabricacao=" + anoDeFabricacao +
                ", fabricante='" + fabricante + '\'' +
                ", proprietario=" + proprietario +
                '}';
    }
}
