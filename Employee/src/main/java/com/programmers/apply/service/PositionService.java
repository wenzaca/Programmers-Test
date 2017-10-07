
package com.programmers.apply.service;

import com.programmers.apply.entities.Position;

public interface PositionService {
	/**
	 * Get the latitude and the longitude values by the address.
	 * it search on googles maps API for the the latitude and longitude.
	 * if the address doesn't return any location from google it is passed 
	 * a null location 
	 * @param address
	 * @return the position with Latitude and Longitude
	 */
	public Position getLongitudeLatitudeByAdress(String address);
}
