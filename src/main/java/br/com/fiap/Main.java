package br.com.fiap;

import br.com.fiap.asseguravel.model.Imovel;
import br.com.fiap.asseguravel.model.Veiculo;
import br.com.fiap.pessoa.model.PessoaFisica;
import br.com.fiap.pessoa.model.PessoaJuridica;
import br.com.fiap.pessoa.model.Sexo;
import br.com.fiap.seguro.model.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import javax.swing.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {


        EntityManagerFactory factory = Persistence.createEntityManagerFactory("maria-db");
        EntityManager manager = factory.createEntityManager();


        var bruno = new PessoaFisica();
        bruno.setCPF(geraCpf())
                .setSexo(Sexo.MASCULINO)
                .setNome("Bruno Sudré do Nascimento")
                .setNascimento(LocalDate.of(2000, 5, 15));

        var esposa = new PessoaFisica();
        esposa.setSexo(Sexo.FEMININO)
                .setCPF(geraCpf())
                .setNascimento(LocalDate.of(1975, 5, 28))
                .setNome("Patroa Sudré do Nascimento");

        esposa.addFilho(bruno);

        var bene = new PessoaFisica();

        bene.setCPF(geraCpf())
                .setSexo(Sexo.MASCULINO)
                .setNome("Benefrancis do Nascimento")
                .setNascimento(LocalDate.of(1977, 03, 8));
        bene.addFilho(bruno);


        var holding = new PessoaJuridica();
        holding.setCNPJ(geraCNPJ())
                .setNascimento(LocalDate.now().minusYears(new Random().nextLong(99)))
                .setNome("Benezinho Holding");
        holding.addSocio(bene).addSocio(bruno).addSocio(esposa);


        var veiculo = new Veiculo();
        veiculo.setChassis(geraChassis())
                .setFabricante("FIAP")
                .setPlaca("FIK" + new Random().nextInt(9999))
                .setModelo("PUNTO")
                .setAnoDeFabricacao(2023)
                .setProprietario(bruno);

        var corretor = new Corretor();
        corretor.setPessoa(holding)
                .setNumeroSUSEP(String.valueOf(new Random().nextLong(999999999)));

        var sv = new SeguroVeicular();
        sv.setObjeto(veiculo)
                .setContratante(bruno)
                .setCorretor(corretor)
                .setInicioVigencia(LocalDate.now())
                .setFimVigencia(LocalDate.now().plusYears(1));
        sv.addBeneficiario(bruno);

        var imovel = new Imovel();
        imovel.setQtdBanheiros(3)
                .setQtdQuartos(3)
                .setQtdVagasDeGaragem(2)
                .setNumeroRegistroNoCartorio(String.valueOf(new Random().nextLong(999999999)));

        imovel.addProprietario(bene).addProprietario(esposa).addProprietario(bruno);

        var sr = new SeguroResidencial();
        sr.setObjeto(imovel)
                .setContratante(bene)
                .setCorretor(corretor)
                .setInicioVigencia(LocalDate.now())
                .setFimVigencia(LocalDate.now().plusYears(1));
        sr.addBeneficiario(bene);

        var svida = new SeguroVida();
        svida.setObjeto(esposa)
                .setContratante(bene)
                .setCorretor(corretor)
                .setInicioVigencia(LocalDate.now())
                .setFimVigencia(LocalDate.now().plusYears(1));
        sv.addBeneficiario(bene);

        List<Seguro> seguros = Arrays.asList(svida, sv, sr);

        try {
            manager.getTransaction().begin();
            seguros.forEach(manager::persist);
            manager.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    """
                            Erro na persistência! 
                                        
                            Confira se todas as classes estão anotadas corretamente!
                                        
                            veja detalhes no console..."""

            );
            e.printStackTrace();
            System.exit(1);
        }

        //Métodos para consultar aqui:
        // findAll(manager);

        findByID(manager);

        manager.close();
        factory.close();

    }

    private static void findByID(EntityManager manager) {
        Long id = Long.valueOf(JOptionPane.showInputDialog("Informe o ID do Seguro que deseja consultar"));
        Seguro seguro = manager.find(Seguro.class, id);

        if (seguro != null) {
            System.out.println(seguro);
        } else {
            JOptionPane.showMessageDialog(null, "Não encontramos Seguro com o ID: " + id);
        }

    }

    private static void findAll(EntityManager manager) {
        var hql = "FROM Seguro";
        List list = manager.createQuery(hql).getResultList();
        list.forEach(System.out::println);
    }

    private static String geraCpf() {
        var sorteio = new Random();
        var digito = sorteio.nextLong(99);
        var numero = sorteio.nextLong(999999999);
        var cpf = String.valueOf(numero) + "-" + String.valueOf(digito);
        return cpf;
    }

    private static String geraCNPJ() {
        var sorteio = new Random();
        var digito = sorteio.nextLong(99);
        var numero = sorteio.nextLong(999999999);
        var cpf = String.valueOf(numero) + "/0001-" + String.valueOf(digito);
        return cpf;
    }

    private static String geraChassis() {
        var sorteio = new Random();
        var numero = sorteio.nextLong(999999999);
        return String.valueOf(numero);
    }

}