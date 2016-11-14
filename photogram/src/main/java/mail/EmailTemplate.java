package mail;

import java.util.Map;

/**
 * Created by Ilya Evlampiev on 01.11.2015.
 */
public abstract class EmailTemplate {

    private EmailSender sender;

    public abstract String getSubject();

    public abstract String getTemplateAddress();

    public abstract Map<String, String> getParametersMap();

    EmailTemplate(String to) {

        sender= new EmailSender(to, this);
    }

    public void send()
    {
        sender.send();
    }


}
