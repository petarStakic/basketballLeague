package rs.enetel.basketballleague.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UploadedFile
{
	private int length;
	private byte[] bytes;
	private String name;
	private String type;
}
