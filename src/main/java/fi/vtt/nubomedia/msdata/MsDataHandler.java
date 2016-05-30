/*
 * (C) Copyright 2014 Kurento (http://kurento.org/)
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser General Public License
 * (LGPL) version 2.1 which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-2.1.html
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 */

package fi.vtt.nubomedia.graph;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

import org.kurento.client.EventListener;
import org.kurento.client.MediaPipeline;
import org.kurento.client.WebRtcEndpoint;

import java.util.Map;
import java.util.Set;
import org.kurento.jsonrpc.JsonUtils;
import org.kurento.module.msdatamodule.*;
import org.kurento.module.msdatamodule.KmsGGD;
import org.kurento.module.msdatamodule.KmsSGD;
import java.io.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

/**
 * @author Markus Ylikerala 
 * @since 6.0.0
 */
public class MsDataHandler extends BaseHandler {

  private final Logger log = LoggerFactory.getLogger(MsDataHandler.class);
  private static final Gson gson = new GsonBuilder().create();

  @Override
  public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
      JsonObject jsonMessage = gson.fromJson(message.getPayload(), JsonObject.class);           

      switch (jsonMessage.get("id").getAsString()) {
	  /*
      case "tester": 
	  System.err.println("CHANGE TESTER");
	  changeTester(session, jsonMessage);      
	  break;
	  */      
      default:
	  super.handleTextMessage(session, message);
	  break;
      }
  }

    public void createPipeline(UserSession userSession, JsonObject jsonMessage){
	try{
	    WebRtcEndpoint webRtcEndpoint = userSession.getWebRtcEndpoint();	    
	    MediaPipeline mediaPipeline = userSession.getMediaPipeline();
	   	    
	    KmsSGD showFaces = new KmsSGD.Builder(mediaPipeline).build();
	    KmsGGD detectFaces = new KmsGGD.Builder(mediaPipeline).build();
	    webRtcEndpoint.connect(detectFaces);
	    detectFaces.connect(showFaces);
	    showFaces.connect(webRtcEndpoint);	    
	} 
	catch(Exception e){
	    throw new RuntimeException(e);
	}
    }
}
