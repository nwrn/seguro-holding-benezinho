package br.com.fiap.seguro.model;

import br.com.fiap.pessoa.model.Pessoa;
import br.com.fiap.pessoa.model.PessoaFisica;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "TB_SEGURO_VIDA")
@DiscriminatorValue("SEG_VIDA")
public class SeguroVida extends Seguro {

    @ManyToOne(
            fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE}
    )
    @JoinColumn(
            name = "ID_PESSOA",
            referencedColumnName = "ID_PESSOA",
            foreignKey = @ForeignKey(name = "FK_SEGURO_PESSOA")
    )
    private PessoaFisica objeto;


    public SeguroVida() {
    }

    public SeguroVida(Long id, LocalDate inicioVigencia, LocalDate fimVigencia, Pessoa contratante, Corretor corretor, PessoaFisica objeto) {
        super(id, inicioVigencia, fimVigencia, contratante, corretor);
        this.objeto = objeto;
    }


    public PessoaFisica getObjeto() {
        return objeto;
    }

    public SeguroVida setObjeto(PessoaFisica objeto) {
        this.objeto = objeto;
        return this;
    }

    @Override
    public String toString() {
        return "SeguroVida{" +
                "objeto=" + objeto +
                "} " + super.toString();
    }
}
