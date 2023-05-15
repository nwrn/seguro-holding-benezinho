package br.com.fiap.seguro.model;

import br.com.fiap.pessoa.model.Pessoa;
import br.com.fiap.pessoa.model.PessoaFisica;
import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
@Table(name = "TB_Seguro_Vida")
@DiscriminatorValue("Seguro_Vida")
public class SeguroVida extends Seguro {

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST},
            fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_Pessoa",
            referencedColumnName = "ID_Pessoa",
            foreignKey = @ForeignKey(name = "FK_PF_Seguro",
                    value = ConstraintMode.CONSTRAINT))
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
