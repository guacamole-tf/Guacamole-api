-- guacamole_product.category definition
CREATE TABLE IF NOT EXISTS `category`
(
    `id`        bigint      NOT NULL AUTO_INCREMENT,
    `create_at` datetime(6) NOT NULL,
    `update_at` datetime(6) NOT NULL,
    `name`      varchar(255) DEFAULT NULL,
    `parent_id` bigint       DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


-- guacamole_product.product definition

CREATE TABLE IF NOT EXISTS `product`
(
    `id`                     bigint      NOT NULL AUTO_INCREMENT,
    `create_at`              datetime(6) NOT NULL,
    `update_at`              datetime(6) NOT NULL,
    `category_id`            bigint       DEFAULT NULL,
    `description_image_path` varchar(255) DEFAULT NULL,
    `detail_description`     varchar(255) DEFAULT NULL,
    `name`                   varchar(255) DEFAULT NULL,
    `origin_place`           varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


-- guacamole_product.product_item definition

CREATE TABLE IF NOT EXISTS `product_item`
(
    `id`                   bigint      NOT NULL AUTO_INCREMENT,
    `create_at`            datetime(6) NOT NULL,
    `update_at`            datetime(6) NOT NULL,
    `price`                int          DEFAULT NULL,
    `product_id`           bigint       DEFAULT NULL,
    `size_rate`            int          DEFAULT NULL,
    `size_unit`            varchar(255) DEFAULT NULL,
    `stock_id`             bigint       DEFAULT NULL,
    `thumbnail_image_path` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


-- guacamole_product.product_item_policy definition

CREATE TABLE IF NOT EXISTS `product_item_policy`
(
    `id`              bigint      NOT NULL AUTO_INCREMENT,
    `create_at`       datetime(6) NOT NULL,
    `update_at`       datetime(6) NOT NULL,
    `banned_at`       bit(1)       DEFAULT NULL,
    `discount_rate`   int          DEFAULT NULL,
    `discount_unit`   varchar(255) DEFAULT NULL,
    `limit_count`     int          DEFAULT NULL,
    `limit_unit`      varchar(255) DEFAULT NULL,
    `product_item_id` bigint       DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


-- guacamole_product.stock definition

CREATE TABLE IF NOT EXISTS `stock`
(
    `id`        bigint      NOT NULL AUTO_INCREMENT,
    `create_at` datetime(6) NOT NULL,
    `update_at` datetime(6) NOT NULL,
    `count`     int DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;