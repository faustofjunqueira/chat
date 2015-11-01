package chat.dominio.entidade;

import java.io.Serializable;

import chat.util.GeradorSerial;

public abstract class Dominio implements Serializable{

	private Integer Id;
	
	public Dominio() {
		Id = GeradorSerial.AutoIncremento();
	}

	public final Integer getId() {
		return Id;
	}

	public final void setId(Integer id) {
		Id = id;
	}
}
