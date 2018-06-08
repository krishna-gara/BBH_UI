package controllers;

import java.util.UUID;

/**
 * Created by kgara on 5/8/2017.
 */
public class SwiftSearchQuery {

    private String SWIFT_MESSAGE_TYPE;

    private Long SWIFT_MESSAGE_FROM_DATE;

    private Long SWIFT_MESSAGE_TO_DATE;

    public Long SWIFT_MESSAGE_TYPE() {
      return SWIFT_MESSAGE_FROM_DATE;
    }

    public void setSWIFT_MESSAGE_TYPE(String SWIFT_MESSAGE_TYPE) {
      this.SWIFT_MESSAGE_TYPE= SWIFT_MESSAGE_TYPE;
    }

    public Long getSWIFT_MESSAGE_DATE() {
      return SWIFT_MESSAGE_FROM_DATE;
    }

    public void setSWIFT_MESSAGE_FROM_DATE(Long SWIFT_MESSAGE_FROM_DATE) {
      this.SWIFT_MESSAGE_FROM_DATE= SWIFT_MESSAGE_FROM_DATE;
    }


    public Long SWIFT_MESSAGE_TO_DATE() {
      return SWIFT_MESSAGE_TO_DATE;
    }

    public void setSWIFT_MESSAGE_TO_DATE(Long SWIFT_MESSAGE_TO_DATE) {
      this.SWIFT_MESSAGE_TO_DATE= SWIFT_MESSAGE_TO_DATE;
    }

}
