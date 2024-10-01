INSERT INTO `user` (`id`, `username`, `nickname`, `gender`, `dept_id`, `phone`, `email`, `password`, `enabled`, `created_time`, `created_by`, `last_modified_time`, `last_modified_by`) VALUES
    (1, 'cat', 'cat', 0, 1, '13411111111', '', '{noop}admin123', 1, '2024-09-15 00:00:00', 'system', '2024-09-30 07:55:21', 'admin');

INSERT INTO `dept` (`id`, `parent_id`, `name`, `type`, `sort`, `enabled`, `remark`, `created_time`, `created_by`, `last_modified_time`, `last_modified_by`) VALUES
    (1, 0, 'test-dept', 1, 0, 1, '', '2024-09-15 00:00:00', 'system', '2024-09-15 00:00:00', 'system');
