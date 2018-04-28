package ru.mirea;

public class InternalException extends Exception {
    public static final String MNEMO_LENGTH = "Значение параметра 'mnemo' короче 2 символов";
    public static final String NAME_LENGTH  = "Значение параметра 'name' не указано";
    public static final String SEARCH_LENGTH = "Значение параметра 'search' короче 2 символов";
    public static final String TAG_NOT_FOUND = "Тег не найден";
    public static final String OFFSET_NOT_A_NUMBER = "Параметр 'offset' - не число";
    public static final String OFFSET_IS_NEGATIVE = "Параметр 'offset' отрицательный";

    public InternalException(String message) {
        super(message);
    }

}