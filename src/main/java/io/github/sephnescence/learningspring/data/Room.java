package io.github.sephnescence.learningspring.data;

import javax.persistence.*; // Interesting. At some point my IDE decided to just import everything

/**
 * We're defining this class based on the schema definition in schema.sql
 *
 * CREATE TABLE ROOM(
 *   ROOM_ID BIGSERIAL PRIMARY KEY,
 *   NAME VARCHAR(16) NOT NULL,
 *   ROOM_NUMBER CHAR(2) NOT NULL UNIQUE,
 *   BED_INFO CHAR(2) NOT NULL
 * );
 */

@Entity
@Table(name="ROOM")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ROOM_ID")
    private long id;

    @Column(name="NAME")
    private String name;

    @Column(name="ROOM_NUMBER")
    private String roomNumber;

    @Column(name="BED_INFO")
    private String bedInfo;

    /**
     * The following code was done by hitting cmd+n and selecting "Getter and Setter" and selecting the four
     * attributes. Nice
     */

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getBedInfo() {
        return bedInfo;
    }

    public void setBedInfo(String bedInfo) {
        this.bedInfo = bedInfo;
    }

    /**
     * Frank also wants us to generate a new toString method
     */

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", roomNumber='" + roomNumber + '\'' +
                ", bedInfo='" + bedInfo + '\'' +
                '}';
    }
}
