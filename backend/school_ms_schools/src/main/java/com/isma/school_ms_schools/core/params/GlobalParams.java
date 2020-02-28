package com.isma.school_ms_schools.core.params;

import java.text.SimpleDateFormat;

public interface GlobalParams {
    String DATEFORMAT="^(0[1-9]|[12][0-9]|3[01])[- /.]([1-9]|0[1-9]|1[012])[- /.](19|20)\\d\\d$";
    SimpleDateFormat FORMATTER = new SimpleDateFormat("dd-MM-yyyy hh:mm");
}
