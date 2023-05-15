package br.com.fiap.seguro.model;

import br.com.fiap.pessoa.model.Pessoa;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(
        name = "TB_Seguro")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "TP_Seguro")
public abstract class Seguro {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_Seguro")
    @SequenceGenerator(name = "SQ_Seguro", sequenceName = "SQ_Seguro")
    @Column(name = "ID_Seguro")
    private Long id;
    @Column(name = "DT_InicioVigencia")
    private LocalDate inicioVigencia;
    @Column(name = "DT_FimVigencia")
    private LocalDate fimVigencia;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST},
            fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_Pessoa",
            referencedColumnName = "ID_Pessoa",
            foreignKey = @ForeignKey(name = "FK_Pessoa_Seguro",
                    value = ConstraintMode.CONSTRAINT))
    private Pessoa contratante;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST},
            fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_Corretor",
            referencedColumnName = "ID_Corretor",
            foreignKey = @ForeignKey(name = "FK_Corretor",
                    value = ConstraintMode.CONSTRAINT))
    private Corretor corretor;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "TB_Beneficiario",
            joinColumns = {
                    @JoinColumn(
                            name = "ID_Pessoa",
                            referencedColumnName = "ID_Seguro",
                            foreignKey = @ForeignKey(name = "FK_Beneficiario_Seguro")
                    )
            },
            inverseJoinColumns = {
                    @JoinColumn(
                            name = "ID_Seguro",
                            referencedColumnName = "ID_Pessoa",
                            foreignKey = @ForeignKey(name = "FK_Seguro_Beneficiario")
                    )
            }
    )
    private Set<Pessoa> beneficiarios = new LinkedHashSet<>();



    public Seguro() {
    }

    public Seguro(Long id, LocalDate inicioVigencia, LocalDate fimVigencia, Pessoa contratante, Corretor corretor) {
        this.id = id;
        this.inicioVigencia = inicioVigencia;
        this.fimVigencia = fimVigencia;
        this.contratante = contratante;
        this.corretor = corretor;
    }


    public Long getId() {
        return id;
    }

    public Seguro setId(Long id) {
        this.id = id;
        return this;
    }

    public LocalDate getInicioVigencia() {
        return inicioVigencia;
    }

    public Seguro setInicioVigencia(LocalDate inicioVigencia) {
        this.inicioVigencia = inicioVigencia;
        return this;
    }

    public LocalDate getFimVigencia() {
        return fimVigencia;
    }

    public Seguro setFimVigencia(LocalDate fimVigencia) {
        this.fimVigencia = fimVigencia;
        return this;
    }

    public Pessoa getContratante() {
        return contratante;
    }

    public Seguro setContratante(Pessoa contratante) {
        this.contratante = contratante;
        return this;
    }

    public Corretor getCorretor() {
        return corretor;
    }

    public Seguro setCorretor(Corretor corretor) {
        this.corretor = corretor;
        return this;
    }


    public Set<Pessoa> getBeneficiarios() {
        return Collections.unmodifiableSet(beneficiarios);
    }

    public Seguro addBeneficiario(Pessoa pessoa) {
        this.beneficiarios.add(pessoa);
        return this;
    }

    public Seguro removeBeneficiario(Pessoa pessoa) {
        this.beneficiarios.remove(pessoa);
        return this;
    }


    @Override
    public String toString() {
        return "Seguro{" +
                "id=" + id +
                ", inicioVigencia=" + inicioVigencia +
                ", fimVigencia=" + fimVigencia +
                ", contratante=" + contratante +
                ", corretor=" + corretor +
                ", beneficiarios=" + beneficiarios +
                '}';
    }
}
