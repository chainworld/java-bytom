package io.bytom.api;

import com.google.gson.annotations.SerializedName;

/**
 * @author https://github.com/JackyKen
 */
public class Receiver {

	@SerializedName("control_program")
	public String controlProgram;

	public String address;

}
