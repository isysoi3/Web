package by.isysoi.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.*;

/**
 * class that represent client entity
 *
 * @author Ilya Sysoi
 * @version 1.0.0
 */
@XmlAccessorType(XmlAccessType.FIELD)
@Entity(name = "Client")
@Table(name = Client.tableName)
public class Client implements Serializable {

    public static final String tableName = "client";
    private static final long serialVersionUID = 1;
    private static final String fioColumnName = "fio";

    @XmlIDREF
    @OneToMany(mappedBy = "client",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    public List<Bet> bets;
    /**
     * id of client
     */
    @XmlAttribute
    @XmlID
    @Id
    @GeneratedValue
    private int id;
    /**
     * FIO of client
     */
    @Column(name = Client.fioColumnName)
    private String FIO;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFIO() {
        return FIO;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

    @Override
    public String toString() {
        return String.format("Client:" +
                        "\n\tid - %d" +
                        "\n\tfio - %s",
                id, FIO);
    }

}
