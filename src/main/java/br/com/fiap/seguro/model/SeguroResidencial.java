package br.com.fiap.seguro.model;

import br.com.fiap.asseguravel.model.Imovel;
import br.com.fiap.pessoa.model.Pessoa;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "TB_Seguro_Residencial")
@DiscriminatorValue("Seguro_Residencial")
public class SeguroResidencial extends Seguro {

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST},
            fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_Imovel",
            referencedColumnName = "ID_Imovel",
            foreignKey = @ForeignKey(name = "FK_Imovel_Seguro",
                    value = ConstraintMode.CONSTRAINT))
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
