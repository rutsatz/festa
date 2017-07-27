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
@SequenceGenerator(name = "idFestaSeq", sequenceName = "id_Festa_Seq", allocationSize = 1)
public class Festa {

    @Id
    @Column(name = "id_Festa")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idFestaSeq")
    private Integer idFesta;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_Festa", foreignKey = @ForeignKey(name = "id_Festa_fk"))
    private List<Convite> convites = new ArrayList<>();
    
    private String local;

    public Festa() {
    }

    @Override
    public String toString() {
        return idFesta + " - " + local;
    }

    public Integer getIdFesta() {
        return idFesta;
    }

    public void setIdFesta(Integer idFesta) {
        this.idFesta = idFesta;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

}
