package com.phil.rogue.controller;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.phil.rogue.info.Info;
import com.phil.rogue.info.Info.InfoEffet;
import com.phil.rogue.screen.GameScreen;

public class InfoController extends Controller {

	public InfoController(GameScreen game) {
		super(game);
	}

	@Override
	public void update() {

		// MOVE INFO
		for (Info info : this.getGame().getInfoManager().getInfos()) {

			info.move(0f, 1f * info.getVitesse());

			if (info.getEffet() == InfoEffet.TREMBLEMENT) {
				info.getPosition().add(MathUtils.random(-2f, 2f), 0);
			}
			info.decrementeLife(Gdx.graphics.getDeltaTime());
		}

		// DELETE INFO
		List<Info> listInfo = this.getGame().getInfoManager().getInfos();
		for (int i = 0; listInfo.size() > i; i++) {

			Info info = listInfo.get(i);
			if (info.isDead()) {
				this.getGame().getInfoManager().remove(info);
			}
		}

	}
}
