/*
 * Copyright 2005-2014 Red Hat, Inc.
 * Red Hat licenses this file to you under the Apache License, version
 * 2.0 (the "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *    http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.  See the License for the specific language governing
 * permissions and limitations under the License.
 */
package org.hornetq.core.persistence.impl.nullpm;

import org.hornetq.api.core.HornetQBuffers;
import org.hornetq.core.journal.SequentialFile;
import org.hornetq.core.server.LargeServerMessage;
import org.hornetq.core.server.impl.ServerMessageImpl;

/**
 * A NullStorageLargeServerMessage
 * @author <a href="mailto:clebert.suconic@jboss.org">Clebert Suconic</a>
 */
class NullStorageLargeServerMessage extends ServerMessageImpl implements LargeServerMessage
{

   public NullStorageLargeServerMessage()
   {
      super();
   }

   @Override
   public void releaseResources()
   {
   }

   @Override
   public synchronized void addBytes(final byte[] bytes)
   {
      if (buffer == null)
      {
         buffer = HornetQBuffers.dynamicBuffer(bytes.length);
      }

      // expand the buffer
      buffer.writeBytes(bytes);
   }

   @Override
   public void deleteFile() throws Exception
   {
      // nothing to be done here.. we don really have a file on this Storage
   }

   @Override
   public boolean isLargeMessage()
   {
      return true;
   }

   @Override
   public void decrementDelayDeletionCount()
   {

   }

   @Override
   public void incrementDelayDeletionCount()
   {

   }

   @Override
   public synchronized int getEncodeSize()
   {
      return getHeadersAndPropertiesEncodeSize();
   }

   @Override
   public String toString()
   {
      return "LargeServerMessage[messageID=" + messageID + ", durable=" + durable + ", address=" + getAddress()  + ",properties=" + properties.toString() + "]";
   }

   @Override
   public void setPaged()
   {
   }

   @Override
   public void setPendingRecordID(long pendingRecordID)
   {
   }

   @Override
   public long getPendingRecordID()
   {
      return -1;
   }


   @Override
   public SequentialFile getFile()
   {
      return null;
   }

}
