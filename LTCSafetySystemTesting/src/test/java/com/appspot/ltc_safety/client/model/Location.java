/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
/*
 * This code was generated by https://github.com/google/apis-client-generator/
 * (build: 2017-02-15 17:18:02 UTC)
 * on 2017-03-04 at 00:27:54 UTC 
 * Modify at your own risk.
 */

package com.appspot.ltc_safety.client.model;

/**
 * Model definition for Location.
 *
 * <p> This is the Java data model class that specifies how to parse/serialize into the JSON that is
 * transmitted over HTTP when working with the User API. For a detailed explanation see:
 * <a href="https://developers.google.com/api-client-library/java/google-http-java-client/json">https://developers.google.com/api-client-library/java/google-http-java-client/json</a>
 * </p>
 *
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public final class Location extends com.google.api.client.json.GenericJson {

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String facilityName;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String roomName;

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getFacilityName() {
    return facilityName;
  }

  /**
   * @param facilityName facilityName or {@code null} for none
   */
  public Location setFacilityName(java.lang.String facilityName) {
    this.facilityName = facilityName;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getRoomName() {
    return roomName;
  }

  /**
   * @param roomName roomName or {@code null} for none
   */
  public Location setRoomName(java.lang.String roomName) {
    this.roomName = roomName;
    return this;
  }

  @Override
  public Location set(String fieldName, Object value) {
    return (Location) super.set(fieldName, value);
  }

  @Override
  public Location clone() {
    return (Location) super.clone();
  }

}
