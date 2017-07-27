/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

/**
 *
 * @author Rafael Rutsatz
 */
@Entity
public class Convite implements Serializable {

    @EmbeddedId
    private ConvitePK convitePK;

    public Convite() {
    }

    public ConvitePK getConvitePK() {
        return convitePK;
    }

    public void setConvitePK(ConvitePK convitePK) {
        this.convitePK = convitePK;
    }

}
