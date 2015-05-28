package engineer.carrot.warren.warren.irc.messages.core;

import engineer.carrot.warren.warren.irc.Hostmask;
import engineer.carrot.warren.warren.irc.messages.AbstractMessage;
import engineer.carrot.warren.warren.irc.messages.IMessage;
import engineer.carrot.warren.warren.irc.messages.IRCMessage;
import engineer.carrot.warren.warren.irc.messages.MessageCodes;

import javax.annotation.Nonnull;

public class JoinedChannelMessage extends AbstractMessage {
    public Hostmask user;
    public String channel;

    @Override
    public void populateFromIRCMessage(IRCMessage message) {
        // {"prefix":"test!~t@test","parameters":["#test"],"command":"JOIN"}

        this.user = Hostmask.parseFromString(message.prefix);
        this.channel = message.parameters.get(0);
    }

    @Override
    public boolean isMessageWellFormed(@Nonnull IRCMessage message) {
        // {"prefix":"test!~t@test","parameters":["#test"],"command":"JOIN"}
        return (message.isPrefixSetAndNotEmpty() && message.isParametersExactlyExpectedLength(1));
    }

    @Override
    public String getCommandID() {
        return MessageCodes.JOIN;
    }
}
