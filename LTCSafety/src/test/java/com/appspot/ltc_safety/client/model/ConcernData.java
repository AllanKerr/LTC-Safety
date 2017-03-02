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
 * on 2017-03-02 at 23:29:26 UTC 
 * Modify at your own risk.
 */

package com.appspot.ltc_safety.client.model;

/**
 * Model definition for ConcernData.
 *
 * <p> This is the Java data model class that specifies how to parse/serialize into the JSON that is
 * transmitted over HTTP when working with the User API. For a detailed explanation see:
 * <a href="https://developers.google.com/api-client-library/java/google-http-java-client/json">https://developers.google.com/api-client-library/java/google-http-java-client/json</a>
 * </p>
 *
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public final class ConcernData extends com.google.api.client.json.GenericJson {

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String actionsTaken;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String concernNature;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private Location location;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private Reporter reporter;

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getActionsTaken() {
    return actionsTaken;
  }

  /**
   * @param actionsTaken actionsTaken or {@code null} for none
   */
  public ConcernData setActionsTaken(java.lang.String actionsTaken) {
    this.actionsTaken = actionsTaken;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getConcernNature() {
    return concernNature;
  }

  /**
   * @param concernNature concernNature or {@code null} for none
   */
  public ConcernData setConcernNature(java.lang.String concernNature) {
    this.concernNature = concernNature;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public Location getLocation() {
    return location;
  }

  /**
   * @param location location or {@code null} for none
   */
  public ConcernData setLocation(Location location) {
    this.location = location;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public Reporter getReporter() {
    return reporter;
  }

  /**
   * @param reporter reporter or {@code null} for none
   */
  public ConcernData setReporter(Reporter reporter) {
    this.reporter = reporter;
    return this;
  }

  @Override
  public ConcernData set(String fieldName, Object value) {
    return (ConcernData) super.set(fieldName, value);
  }

  @Override
  public ConcernData clone() {
    return (ConcernData) super.clone();
  }

}
