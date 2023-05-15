package br.com.fiap.seguro.model;

import br.com.fiap.asseguravel.model.Veiculo;
import br.com.fiap.pessoa.model.Pessoa;
import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
@Table(name = "TB_Seguro_Veicular")
@DiscriminatorValue("Seguro_Veicular")
public class SeguroVeicular extends Seguro {

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST},
            fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_Veiculo",
            referencedColumnName = "ID_Veiculo",
            foreignKey = @ForeignKey(name = "FK_Veiculo_Seguro",
                    value = ConstraintMode.CONSTRAINT))
    private Veiculo objeto;

    public SeguroVeicular() {
    }

    public SeguroVeicular(Long id, LocalDate inicioVigencia, LocalDate fimVigencia, Pessoa contratante, Corretor corretor, Veiculo objeto) {
        super(id, inicioVigencia, fimVigencia, contratante, corretor);
        this.objeto = objeto;
    }

    public Veiculo getObjeto() {
        return objeto;
    }

    public SeguroVeicular setObjeto(Veiculo objeto) {
        this.objeto = objeto;
        return this;
    }

    @Override
    public String toString() {
        return "SeguroVeicular{" +
                "objeto=" + objeto +
                "} " + super.toString();
    }
}
