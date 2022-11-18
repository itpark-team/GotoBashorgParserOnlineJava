package org.example.service.menupoints;

import org.example.parser.BashorgParser;
import org.example.statemachine.State;
import org.example.statemachine.TransmittedData;
import org.example.util.ButtonsStorage;
import org.example.util.DialogStringsStorage;
import org.example.util.InlineKeyboardsMarkupStorage;
import org.example.util.SystemStringsStorage;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.List;

public class MainMenuService {
    private BashorgParser bashorgParser;

    public MainMenuService()  {
        bashorgParser = new BashorgParser();
    }

    public SendMessage processCommandStart(String command, TransmittedData transmittedData) {
        SendMessage message = new SendMessage();
        message.setChatId(transmittedData.getChatId());

        if (!command.equals(SystemStringsStorage.CommandStart)) {
            message.setText(DialogStringsStorage.CommandStartError);
            return message;
        }

        return SharedService.goToProcessClickOnInlineButtonInMenuMyCards(transmittedData);
    }

    public SendMessage processClickInMenuMain(String callBackData, TransmittedData transmittedData) throws Exception {
        SendMessage message = new SendMessage();
        message.setChatId(transmittedData.getChatId());

        if (callBackData.equals(ButtonsStorage.GetRandomJokeInMenuMain.getCallBackData())) {

            message.setText(bashorgParser.getRandomJoke());

            return message;
        }

        throw new Exception("Ошибка распознавания callBackData");
    }
}
