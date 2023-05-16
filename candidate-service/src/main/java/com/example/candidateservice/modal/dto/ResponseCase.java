package com.example.candidateservice.modal.dto;

import com.example.candidateservice.utils.Constant;
import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ResponseCase {

    SUCCESS(1000, Constant.SUCCESS_MSG),
    ADD_SUCCESS(2000, Constant.SUCCESS_MSG),
    UPDATE_SUCCESS(2001, Constant.SUCCESS_MSG),
    DELETE_SUCCESS(2002, Constant.SUCCESS_MSG),
    DELETE_FAILED(2005, "ERROR"),
    OBJECT_NULL(2003, "ERROR"),
    PASSWORD_NOT_RIGHT(1007, "Password is incorrect !"),
    USERNAME_IS_USED(1, "Username is used"),
    ERROR(4, "Error"),
    ZIP_CODE_NOTFOUND(1013, "Cannot find zip_code"),
    DUPLICATE(1020, "Duplicate"),
    EMPTY(1021, "Response is empty"),
    INVALID_FILES_SIZE(1029, "Invalid number of files!"),
    NOT_FOUND(1030, "Cannot found entity!"),
    IN_USE(1031, "In use!"),

    TABLE_IS_BUSY(1033, "This table is busy!"),
    HAS_NO_CONFIG(1034, "Cant find common config on this day!"),
    WORK_OFF_DAY(1035, "This is work off day!"),

    NOT_FOUND_CUSTOMER(1036, "Not found customer for booking!"),

    INVALID_DATE_RANGE(1038, "invalid date range!"),
    DATE_MUST_NOT_OVERLAP(1039, "Date must not overlap!"),
    OUT_OF_FRAME_CAN_CONSECUTIVE(1041, "Out of frame can consecutive!"),
    INVALID_NUMBER_FRAME_CONSECUTIVE(1045, "invalid number frame consecutive!"),

    NO_SUCH_FIELD(1042, "No such field when try to get value via reflection!"),
    DUPLICATE_DATA(1043, "Duplicate data!"),
    WORKING_TIME_INVALID(1044, "Working time is invalid!"),
    WORKING_TIME_INVALID_DUE_TO_BREAK_TIME(1045, "Working time is invalid due to breaking time conflict with booking"),

    COMMON_CATEGORY_GROUP_USED(1046, "Common category group is used"),
    INTERNAL_EXCEPTION(500, "Internal Server Error!"),
    BAD_REQUEST(400, "Invalid Parameters!"),

    CANNOT_CREATE_CUSTOMER(1047, "Cannot create customer!"),
    ID_NULL(1048, "Id is null!"),
    IN_VALID_CUSTOMER_NAME_KANA_AND_NAME(1049, "Name or NameKana must be not null"),
    IN_VALID_STORE_CODE(1050, "store code must be not null!"),
    STAFF_CODE_IS_EXIST(1051, "store code is exist!"),

    UNAUTHENTICATED(1999, "Unauthenticated"),
    DELETE_BOTH_BOOKING_AND_CONFIG_SUCCESS(2007, "Delete active config when no booking remaining"),
    AUTHENTICATE_FAILED(13, "AUTHENTICATE_FAILED");

    public final int code;
    public final String message;

    ResponseCase(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
