/*******************************************************************************
 * Copyright 2011 See AUTHORS file.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package ie.tcd.mengxia;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Animation {
	final TextureRegion[] keyFrames;
	final float frameDuration;

	public Animation(float frameDuration, TextureRegion... keyFrames) {
		this.frameDuration = frameDuration;
		this.keyFrames = keyFrames;
	}

	public TextureRegion getKeyFrame (float stateTime, Mode mode) {
		int frameNumber = (int)(stateTime / frameDuration);

		if (mode == Mode.NON_LOOPING) {
			frameNumber = Math.min(keyFrames.length - 1, frameNumber);
		} else {
			frameNumber = frameNumber % keyFrames.length;
		}

		return keyFrames[frameNumber];
	}

	public static enum Mode {
		LOOPING,
		NON_LOOPING;
	}
}