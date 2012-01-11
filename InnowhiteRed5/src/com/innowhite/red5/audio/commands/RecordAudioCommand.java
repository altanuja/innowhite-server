/** 
* ===License Header===
*
* BigBlueButton open source conferencing system - http://www.bigbluebutton.org/
*
* Copyright (c) 2010 BigBlueButton Inc. and by respective authors (see below).
*
* This program is free software; you can redistribute it and/or modify it under the
* terms of the GNU Lesser General Public License as published by the Free Software
* Foundation; either version 2.1 of the License, or (at your option) any later
* version.
*
* BigBlueButton is distributed in the hope that it will be useful, but WITHOUT ANY
* WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
* PARTICULAR PURPOSE. See the GNU Lesser General Public License for more details.
*
* You should have received a copy of the GNU Lesser General Public License along
* with BigBlueButton; if not, see <http://www.gnu.org/licenses/>.
* 
* ===License Header===
*/
package com.innowhite.red5.audio.commands;

public class RecordAudioCommand extends FreeswitchCommand {
	
  //  private final Integer participant;
    private final Boolean recordStatus;
    private final String RECORD ="uuid_record";

    public RecordAudioCommand(String room,   Integer requesterId, Boolean recordStatus) {
            super(room, requesterId);
           
            this.recordStatus = recordStatus;
    }

    @Override
    public String getCommandArgs() {
            String action = "stop";
            if (recordStatus) action = "start";

            return RECORD + SPACE + room+ SPACE + action ;
    }

}
