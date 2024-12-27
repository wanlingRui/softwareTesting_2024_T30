package org.ms.seedsorter;

import lombok.Data;

import java.util.Set;

/**
 * 边缘覆盖信息
 */
@Data
public class Coverage {
    Set<Integer> edgeHits;
}
