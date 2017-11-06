package ec.icon;

import com.joanzapata.iconify.Icon;

/**
 * Created by qilee on 2017/11/6.
 */

public enum  EcIcons implements Icon{
    ec_scan('\ue60c'),
    ali_pay('\ue67c');

    private char character;
    EcIcons(char character){
        this.character = character;
    }
    @Override
    public String key() {
        return name().replace('_', '-');
    }

    @Override
    public char character() {
        return character;
    }
}
