package org.ms.seedsorter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 表示输入种子
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Seed {

    byte[] content;
}
