package com.phil.rogue.info;

import java.util.ArrayList;
import java.util.List;

public class InfoManager {

	private final List<Info> infos;

	public InfoManager() {
		this.infos = new ArrayList<Info>();
	}

	public void addInfo(Info i) {
		this.infos.add(i);
	}

	public void remove(Info i) {
		this.infos.remove(i);
	}

	public List<Info> getInfos() {
		return infos;
	}

}
