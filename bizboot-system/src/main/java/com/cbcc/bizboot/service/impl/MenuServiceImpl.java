package com.cbcc.bizboot.service.impl;

import com.cbcc.bizboot.entity.Menu;
import com.cbcc.bizboot.exception.BadRequestException;
import com.cbcc.bizboot.repository.MenuRepository;
import com.cbcc.bizboot.service.MenuService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;

    public MenuServiceImpl(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @Override
    public List<Menu> find(Menu menu) {
        return menuRepository.findAll(Example.of(menu));
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
        Menu menuToUpdate = optionalMenu.get();
        BeanUtils.copyProperties(menu, menuToUpdate);
        menuRepository.save(menuToUpdate);
    }

    @Override
    @Transactional
    public void delete(long id) {
        boolean existed = menuRepository.existsById(id);
        if (!existed) {
            return;
        }
        List<Long> menuIds = findSubMenuIds(id);
        menuIds.add(id);
        menuRepository.deleteAllById(menuIds);
    }

    /**
     * 根据菜单 id 查找所有子菜单 id
     */
    private List<Long> findSubMenuIds(long id) {
        List<Long> subMenuIds = menuRepository.findIdsByParentId(id);
        List<Long> allSubMenuIds = new ArrayList<>(subMenuIds);
        for (long subMenuId : subMenuIds) {
            allSubMenuIds.addAll(findSubMenuIds(subMenuId));
        }
        return allSubMenuIds;
    }
}
