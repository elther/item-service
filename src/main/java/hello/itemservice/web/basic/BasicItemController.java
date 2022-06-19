package hello.itemservice.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

    private final ItemRepository itemRepository;

    /**
     * test용
     */
    @PostConstruct
    public void init(){
        itemRepository.save(new Item("itemA", 10000,10));
        itemRepository.save(new Item("itemB", 20000,20));
    }

    @GetMapping
    public String items(Model model){
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

    @GetMapping("/{id}")
    public String item(@PathVariable Long id, Model model){
        Item findItem = itemRepository.findById(id);
        model.addAttribute("item", findItem);
        return "basic/item";
    }

//    @PostMapping("/add")
//    public String addItem(@ModelAttribute Item item){
//        itemRepository.save(item);
//        return "basic/item";
//    }

    @PostMapping("/add")
    public String addItem(Item item){
        itemRepository.save(item);
        return "basic/item";
    }

    @GetMapping("/add")
    public String add(Model model){
        return "basic/addForm";
    }

    @GetMapping("/{id}/edit")
    public String editItem(@PathVariable Long id, Model model){
        model.addAttribute("item", itemRepository.findById(id));
        return "basic/editForm";
    }

    @PostMapping("/{id}/edit")
    public String edit(@PathVariable Long id, @ModelAttribute Item item){
        itemRepository.update(id, item);
        return "redirect:/basic/items/{id}";
    }



}
