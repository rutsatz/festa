/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;

/**
 *
 * @author Rafael Rutsatz
 */
@Embeddable
public class ConvitePK implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "id_Pessoa")
    @JoinColumn(name = "id_Pessoa")
    private Integer idPessoa;

    @Column(name = "id_Festa")
    @JoinColumn(name = "id_Festa")
    private Integer idFesta;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.idPessoa);
        hash = 97 * hash + Objects.hashCode(this.idFesta);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ConvitePK other = (ConvitePK) obj;
        if (!Objects.equals(this.idPessoa, other.idPessoa)) {
            return false;
        }
        if (!Objects.equals(this.idFesta, other.idFesta)) {
            return false;
        }
        return true;
    }

    public Integer getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Integer idPessoa) {
        this.idPessoa = idPessoa;
    }

    public Integer getIdFesta() {
        return idFesta;
    }

    public void setIdFesta(Integer idFesta) {
        this.idFesta = idFesta;
    }

}
