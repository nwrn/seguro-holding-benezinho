package br.com.fiap.asseguravel.model;

import br.com.fiap.pessoa.model.Pessoa;
import jakarta.persistence.*;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "TB_IMOVEL", uniqueConstraints = {

        @UniqueConstraint(name = "UK_IMOVEL", columnNames = "NR_REGISTRO")
})
public class Imovel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_IMOVEL")
    @SequenceGenerator(
            name = "SQ_IMOVEL",
            sequenceName = "SQ_IMOVEL",
            allocationSize = 1,
            initialValue = 1)
    @Column(name = "ID_IMOVEL")
    private Long id;

    @Column(name = "NR_QUARTOS")
    private int qtdQuartos;

    @Column(name = "NR_BANHEIROS")
    private int qtdBanheiros;

    @Column(name = "NR_VAGAS")
    private int qtdVagasDeGaragem;

    @Column(name = "NR_REGISTRO")
    private String numeroRegistroNoCartorio;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "TB_PROPRIETARIOS",
                //A Própria classe. Imóvel (Primeiro eu! Depois as dividas!!!)
            joinColumns = {
                    @JoinColumn(
                            name = "ID_IMOVEL",
                            referencedColumnName = "ID_IMOVEL",
                            foreignKey = @ForeignKey(name = "FK_PROP_IMOVEL")
                    )
            },
            //A Outra classe. Pessoa (Olha lá em baixo o tipo)
            inverseJoinColumns = {
                  @JoinColumn(
                          name = "ID_PROPRIETARIO",
                          referencedColumnName = "ID_PESSOA",
                          foreignKey = @ForeignKey(name = "FK_PROP_PESSOA")
                  )
            }
    )
    protected Set<Pessoa> proprietarios = new LinkedHashSet<>();


    public Imovel() {
    }


    public Imovel(Long id, int qtdQuartos, int qtdBanheiros, int qtdVagasDeGaragem, String numeroRegistroNoCartorio, Set<Pessoa> proprietarios) {
        this.id = id;
        this.qtdQuartos = qtdQuartos;
        this.qtdBanheiros = qtdBanheiros;
        this.qtdVagasDeGaragem = qtdVagasDeGaragem;
        this.numeroRegistroNoCartorio = numeroRegistroNoCartorio;
        this.proprietarios = proprietarios;
    }

    public Long getId() {
        return id;
    }

    public Imovel setId(Long id) {
        this.id = id;
        return this;
    }

    public int getQtdVagasDeGaragem() {
        return qtdVagasDeGaragem;
    }

    public Imovel setQtdVagasDeGaragem(int qtdVagasDeGaragem) {
        this.qtdVagasDeGaragem = qtdVagasDeGaragem;
        return this;
    }

    public int getQtdQuartos() {
        return qtdQuartos;
    }

    public Imovel setQtdQuartos(int qtdQuartos) {
        this.qtdQuartos = qtdQuartos;
        return this;
    }

    public int getQtdBanheiros() {
        return qtdBanheiros;
    }

    public Imovel setQtdBanheiros(int qtdBanheiros) {
        this.qtdBanheiros = qtdBanheiros;
        return this;
    }


    public String getNumeroRegistroNoCartorio() {
        return numeroRegistroNoCartorio;
    }

    public Imovel setNumeroRegistroNoCartorio(String numeroRegistroNoCartorio) {
        this.numeroRegistroNoCartorio = numeroRegistroNoCartorio;
        return this;
    }


    public Set<Pessoa> getProprietarios() {
        return Collections.unmodifiableSet(proprietarios);
    }


    public Imovel addProprietario(Pessoa pessoa) {
        this.proprietarios.add(pessoa);
        return this;
    }


    public Imovel removerProprietario(Pessoa pessoa) {
        this.proprietarios.remove(pessoa);
        return this;
    }

    @Override
    public String toString() {
        return "Imovel{" +
                "id=" + id +
                ", qtdQuartos=" + qtdQuartos +
                ", qtdBanheiros=" + qtdBanheiros +
                ", qtdVagasDeGaragem=" + qtdVagasDeGaragem +
                ", numeroRegistroNoCartorio='" + numeroRegistroNoCartorio + '\'' +
                ", proprietarios=" + proprietarios +
                '}';
    }
}
