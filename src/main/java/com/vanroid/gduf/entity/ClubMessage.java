package com.vanroid.gduf.entity;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Table(name = "gd_message_club")
@PrimaryKeyJoinColumn(name = "cmsg_id")
public class ClubMessage {
	

}
