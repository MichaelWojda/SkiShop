package pl.san.mw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.san.mw.model.AppUser;
import pl.san.mw.model.Item;
import pl.san.mw.repositories.ItemRepository;
import pl.san.mw.repositories.UserRepository;

import java.util.List;
//autor Michał Wojda indeks:23512
@Controller
public class ItemController {

    private ItemRepository itemRepository;
    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setItemRepository(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @GetMapping("/inventory")
    public String loadInventory(Model model){
        List<Item> list = itemRepository.getAll();
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedInUser.getName();
        model.addAttribute("itemList",list);
        model.addAttribute("username",username);
        return "inventory";
    }
    @GetMapping("/inventory/rent")
    public String rentItem(@RequestParam String username, @RequestParam Long id){
        Item item = itemRepository.getById(id);
        AppUser user = userRepository.findByUsername(username);
        item.setPersonWhoHasIt(user);
        item.setRent(true);
        itemRepository.update(item);
        return "index";

    }

    @GetMapping("/add")
    public String loadAddForm(Model model){
        model.addAttribute("item",new Item());
        return "add";
    }

    @PostMapping("/inventory")
    public String addItem(@ModelAttribute Item item){
        itemRepository.createItem(item);
        return "redirect:add";
    }
    @GetMapping("/items")
    public String loadItems(Model model){
        List<Item> list = itemRepository.getAll();
        model.addAttribute("itemList",list);
        return "items";
    }
    @GetMapping("/items/return")
    public String returnItem(@RequestParam Long id){
        Item item = itemRepository.getById(id);
        item.setPersonWhoHasIt(null);
        item.setRent(false);
        itemRepository.update(item);
        return "redirect:/items";

    }




}
