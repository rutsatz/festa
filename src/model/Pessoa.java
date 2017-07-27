/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author Rafael Rutsatz
 */
@Entity
@SequenceGenerator(name = "idPessoaSeq", sequenceName = "id_Pessoa_Seq", allocationSize = 1)
public class Pessoa {

    @Id
    @Column(name = "id_Pessoa")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idPessoaSeq")
    private Integer idPessoa;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_Pessoa", foreignKey = @ForeignKey(name = "id_Pessoa_fk"))
    private List<Convite> convites = new ArrayList<>();

    private String nome;

    private String CPF;

    public Pessoa() {
    }

    @Override
    public String toString() {
        return idPessoa + " - " + CPF + " " + nome;
    }

    public Integer getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Integer idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

}
