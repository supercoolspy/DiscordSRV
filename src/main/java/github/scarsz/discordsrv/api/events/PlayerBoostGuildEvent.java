/*
 * DiscordSRV - https://github.com/DiscordSRV/DiscordSRV
 *
 * Copyright (C) 2016 - 2022 Austin "Scarsz" Shapiro
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 */

package github.scarsz.discordsrv.api.events;

import github.scarsz.discordsrv.api.Cancellable;
import lombok.Getter;
import lombok.Setter;
import net.dv8tion.jda.api.events.guild.member.update.GuildMemberUpdateBoostTimeEvent;

public class PlayerBoostGuildEvent extends DiscordEvent<GuildMemberUpdateBoostTimeEvent> implements Cancellable {

        @Getter
        @Setter
        private boolean cancelled;

        @Getter final private boolean isBoosting;

        public PlayerBoostGuildEvent(GuildMemberUpdateBoostTimeEvent jdaEvent) {
            super(jdaEvent.getJDA());
            if (jdaEvent.getOldTimeBoosted() == null) {
                isBoosting = true;
                return;
            }
            if (jdaEvent.getNewTimeBoosted() == null) {
                isBoosting = false;
                return;
            }
            isBoosting = jdaEvent.getOldTimeBoosted().isBefore(jdaEvent.getNewTimeBoosted());
        }
}