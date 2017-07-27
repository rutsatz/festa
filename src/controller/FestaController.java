/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import festa.JpaUtil;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.event.ChangeEvent;
import model.Convite;
import model.ConvitePK;
import model.Festa;
import model.Pessoa;

/**
 *
 * @author Rafael Rutsatz
 */
public class FestaController implements Initializable {

    @FXML
    public TextField txCPF;

    @FXML
    public TextField txNome;

    @FXML
    public TextField txLocal;

    @FXML
    public ListView liConvidarFesta;

    @FXML
    public ListView liConvidarPessoa;

    @FXML
    public ListView liFestas;

    @FXML
    public ListView liConvidados;

    @FXML
    public Tab tabConvites;

    @FXML
    public Tab tabCadastrarPessoa;

    @FXML
    public Tab tabCadastrarFesta;

    @FXML
    public Tab tabConvidarFesta;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    public void salvarPessoa() {
        EntityManager entityManager = JpaUtil.getEntityManager();
        entityManager.getTransaction().begin();

        Pessoa pessoa = new Pessoa();
        pessoa.setCPF(txCPF.getText());
        pessoa.setNome(txNome.getText());

        entityManager.persist(pessoa);

        entityManager.getTransaction().commit();

        JpaUtil.closeEntityManager();
    }

    @FXML
    public void salvarFesta() {
        EntityManager entityManager = JpaUtil.getEntityManager();
        entityManager.getTransaction().begin();

        Festa festa = new Festa();
        festa.setLocal(txLocal.getText());

        entityManager.persist(festa);

        entityManager.getTransaction().commit();

        JpaUtil.closeEntityManager();
    }

    @FXML
    public void selecionarConvites() {
        if (tabConvites.isSelected()) {
            EntityManager entityManager = JpaUtil.getEntityManager();

            String consulta;
            Query query;

            consulta = "select f from Festa f";
            query = entityManager.createQuery(consulta);
            List l = query.getResultList();

            ObservableList<Festa> festas = FXCollections.observableArrayList(l);

            liFestas.setItems(festas);
        }
    }

    @FXML
    public void selecionarCadastrarPessoa() {
        System.out.println("Selecionou cadastrar pessoa");
    }

    @FXML
    public void selecionarCadastrarFesta() {
        System.out.println("Selecionou cadastrar festa");
    }

    @FXML
    /*
    Chamado no botão Convidar da aba Convidar para Festa
    Insere uma pessoa no convite da festa selecionada
     */
    public void convidarFesta() {
        Festa festa = (Festa) liConvidarFesta.getSelectionModel().getSelectedItem();
        Pessoa pessoa = (Pessoa) liConvidarPessoa.getSelectionModel().getSelectedItem();

        ConvitePK convitePK = new ConvitePK();
        convitePK.setIdFesta(festa.getIdFesta());
        convitePK.setIdPessoa(pessoa.getIdPessoa());

        Convite convite = new Convite();
        convite.setConvitePK(convitePK);

        EntityManager entityManager = JpaUtil.getEntityManager();
        entityManager.getTransaction().begin();

        entityManager.persist(convite);

        entityManager.getTransaction().commit();

        JpaUtil.closeEntityManager();
    }

    @FXML
    /*
     Chamado ao selecionar a aba Convidar para Festa
     Busca os dados do banco e popula as ListViews
     */
    public void selecionarConvidarFesta(Event e) {

        if (tabConvidarFesta.isSelected()) {

            EntityManager entityManager = JpaUtil.getEntityManager();

            String consulta;
            Query query;

            consulta = "select f from Festa f";
            query = entityManager.createQuery(consulta);
            List l = query.getResultList();

            ObservableList<Festa> festas = FXCollections.observableArrayList(l);

            liConvidarFesta.setItems(festas);

            consulta = "select p from Pessoa p";
            query = entityManager.createQuery(consulta);
            List p = query.getResultList();

            ObservableList<Pessoa> pessoas = FXCollections.observableArrayList(p);

            liConvidarPessoa.setItems(pessoas);

        }
    }

    @FXML
    public void conviteSelecionarFesta(Event e) {

        boolean atualizar = false;
        if (e.getEventType() == KeyEvent.KEY_RELEASED) {
            KeyEvent ke = (KeyEvent) e;
            if (ke.getCode() == KeyCode.DOWN || ke.getCode() == KeyCode.UP) {
                atualizar = true;
            }

        } else if (e.getEventType() == MouseEvent.MOUSE_CLICKED) {
            atualizar = true;
        }

        if (atualizar) {
            if (liFestas.getSelectionModel().getSelectedItems().size() > 0) {
                Festa festa = (Festa) liFestas.getSelectionModel().getSelectedItem();

                EntityManager entityManager = JpaUtil.getEntityManager();

                String consulta;
                Query query;

                consulta = "select p "
                        + "   from Pessoa p"
                        + "   join p.convites c"
                        + "  where c.convitePK.idFesta = :idFesta";
                query = entityManager.createQuery(consulta, Pessoa.class);
                query.setParameter("idFesta", festa.getIdFesta());
                List p = query.getResultList();

                ObservableList<Pessoa> pessoas = FXCollections.observableArrayList(p);

                liConvidados.setItems(pessoas);
            }
        }
    }
}
