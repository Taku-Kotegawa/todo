package com.example.app.echo;

import lombok.Data;

import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * エコー画面のフォームビーン.
 */
@Data
public class EchoForm implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 名前.
     */
    @NotBlank
    @Size(min = 1, max = 5)
    private String name;

}
