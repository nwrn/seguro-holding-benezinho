package br.com.fiap.seguro.model;

import br.com.fiap.pessoa.model.Pessoa;
import jakarta.persistence.*;


@Entity
@Table(
        name = "TB_Corretor",
        uniqueConstraints = {
                @UniqueConstraint(name = "UK_SUSEP",
                        columnNames = "NR_SUSEP")
        })
public class Corretor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_Corretor")
    @SequenceGenerator(name = "SQ_Corretor", sequenceName = "SQ_Corretor")
    @Column(name = "ID_Corretor")
    private Long id;

    @Column(name = "NR_SUSEP")
    private String numeroSUSEP;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST},
            fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_Pessoa",
            referencedColumnName = "ID_Pessoa",
            foreignKey = @ForeignKey(name = "FK_Pessoa_Corretor",
                    value = ConstraintMode.CONSTRAINT))
    private Pessoa pessoa;


    public Corretor() {
    }

    public Corretor(long id, String numeroSUSEP, Pessoa pessoa) {
        this.id = id;
        this.numeroSUSEP = numeroSUSEP;
        this.pessoa = pessoa;
    }

    public long getId() {
        return id;
    }

    public Corretor setId(long id) {
        this.id = id;
        return this;
    }

    public String getNumeroSUSEP() {
        return numeroSUSEP;
    }

    public Corretor setNumeroSUSEP(String numeroSUSEP) {
        this.numeroSUSEP = numeroSUSEP;
        return this;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public Corretor setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
        return this;
    }

    @Override
    public String toString() {
        return "Corretor{" +
                "id=" + id +
                ", numeroSUSEP='" + numeroSUSEP + '\'' +
                ", pessoa=" + pessoa +
                '}';
    }
}
