package br.com.fiap.seguro.model;

import br.com.fiap.asseguravel.model.Veiculo;
import br.com.fiap.pessoa.model.Pessoa;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "TB_SEGURO_VEICULO")
@DiscriminatorValue("SEG_VEICULO")
public class SeguroVeicular extends Seguro {

    @ManyToOne(
            fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE}
    )
    @JoinColumn(
            name = "ID_VEICULO",
            referencedColumnName = "ID_VEICULO",
            foreignKey = @ForeignKey(name = "FK_SEGURO_VEICULO")
    )
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
