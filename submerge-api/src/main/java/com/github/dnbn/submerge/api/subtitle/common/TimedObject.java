package com.github.dnbn.submerge.api.subtitle.common;

import java.io.Serializable;
import java.util.Comparator;

/**
 * 
 * Simple object that contains timed start ant end
 */
public interface TimedObject extends Serializable, Comparable<TimedObject>, Comparator<TimedObject> {

	/**
	 * Return the time elapsed during script playback at which the text will appear
	 * onscreen.
	 * 
	 * @return start time
	 */
	long getStart();

	/**
	 * Return the time elapsed during script playback at which the text will disappear
	 * offscreen.
	 * 
	 * @return end time
	 */
	long getEnd();

	/**
	 * Set the time elapsed during script playback at which the text will appear onscreen.
	 * 
	 * @param start time
	 */
	void setStart(long start);

	/**
	 * Set the time elapsed during script playback at which the text will disappear
	 * offscreen.
	 * 
	 * @param end time
	 */
	void setEnd(long end);
}