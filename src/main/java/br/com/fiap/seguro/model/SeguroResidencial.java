package br.com.fiap.seguro.model;

import br.com.fiap.asseguravel.model.Imovel;
import br.com.fiap.pessoa.model.Pessoa;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "TB_SEGURO_RESIDENCIAL")
@DiscriminatorValue("SEG_RESIDENCIAL")
public class SeguroResidencial extends Seguro {

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "ID_IMOVEL",
            referencedColumnName = "ID_IMOVEL",
            foreignKey = @ForeignKey(name = "FK_SEG_IMOVEL")
    )
    private Imovel objeto;

    public SeguroResidencial() {
    }

    public SeguroResidencial(Long id, LocalDate inicioVigencia, LocalDate fimVigencia, Pessoa contratante, Corretor corretor, Imovel objeto) {
        super(id, inicioVigencia, fimVigencia, contratante, corretor);
        this.objeto = objeto;
    }

    public Imovel getObjeto() {
        return objeto;
    }

    public SeguroResidencial setObjeto(Imovel objeto) {
        this.objeto = objeto;
        return this;
    }

    @Override
    public String toString() {
        return "SeguroResidencial{" +
                "objeto=" + objeto +
                "} " + super.toString();
    }
}
