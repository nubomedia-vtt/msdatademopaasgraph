/**
 * This file is generated with Kurento-maven-plugin.
 * Please don't edit.
 */
package org.kurento.module.msdata;

import org.kurento.client.*;

/**
 *
 * KmsMsData interface. Documentation about the module
 *
 **/
@org.kurento.client.internal.RemoteClass
public interface KmsMsData extends MediaElement {


    



    public class Builder extends AbstractBuilder<KmsMsData> {

/**
 *
 * Creates a Builder for KmsMsData
 *
 **/
    public Builder(org.kurento.client.MediaPipeline mediaPipeline){

      super(KmsMsData.class,mediaPipeline);

      props.add("mediaPipeline",mediaPipeline);
    }

	public Builder withProperties(Properties properties) {
    	return (Builder)super.withProperties(properties);
  	}

	public Builder with(String name, Object value) {
		return (Builder)super.with(name, value);
	}
	
    }


}