package com.cbcc.bizboot.service.impl;

import com.cbcc.bizboot.entity.Menu;
import com.cbcc.bizboot.exception.BadRequestException;
import com.cbcc.bizboot.repository.MenuRepository;
import com.cbcc.bizboot.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.Optional;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Override
    public Page<Menu> find(Menu menu, Pageable pageable) {
        return menuRepository.findAll(Example.of(menu), pageable);
    }

    @Override
    public Menu get(long id) {
        return menuRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(MessageFormat.format("菜单不存在. id = {0}", id)));
    }

    @Override
    public Menu create(Menu menu) {
        return menuRepository.save(menu);
    }

    @Override
    public void update(Menu menu) {
        Optional<Menu> optionalMenu = menuRepository.findById(menu.getId());
        if (optionalMenu.isEmpty()) {
            throw new BadRequestException(MessageFormat.format("菜单不存在. id = {0}", menu.getId()));
        }
        Menu MenuToUpdate = optionalMenu.get();
        MenuToUpdate.setParentId(menu.getParentId());
        MenuToUpdate.setTitle(menu.getTitle());
        MenuToUpdate.setPath(menu.getPath());
        MenuToUpdate.setSort(menu.getSort());
        MenuToUpdate.setEnabled(menu.getEnabled());
        menuRepository.save(MenuToUpdate);
    }

    @Override
    public void delete(long id) {
        menuRepository.deleteById(id);
    }
}
