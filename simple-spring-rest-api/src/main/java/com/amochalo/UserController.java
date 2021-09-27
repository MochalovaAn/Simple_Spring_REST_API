package com.amochalo;

import com.amochalo.error.UserNotFoundException;
import com.amochalo.error.UsersUnSupportedFieldPatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static java.lang.Integer.parseInt;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository repository;

    private final ApplicationContext context;

    private HashMap<String, Action> actionMap;

    @Autowired
    public UserController(UserRepository repository, ApplicationContext context) {
        this.repository = repository;
        this.context = context;
        Collection<Action> actionCollection = context.getBeansOfType(Action.class).values();
        actionMap = new HashMap<>();
        for (Action act : actionCollection) {
            actionMap.put(act.getKey(), act);
        }
    }

    private List<User> toList(final Iterable<User> iterable) throws NullPointerException {
        return StreamSupport.stream(iterable.spliterator(), false)
                .collect(Collectors.toList());
    }

    // Find
    @GetMapping()
    List<User> findAll() {
        return toList(repository.findAll());
    }

    // Save
    @PostMapping()
    //return 201 instead of 200
    @ResponseStatus(HttpStatus.CREATED)
    User newUser(@RequestBody User newUser) {
        return repository.save(newUser);
    }

    // Find
    @GetMapping("/{id}")
    User findOne(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    // Save or update
    @PutMapping("{id}")
    User saveOrUpdate(@RequestBody User newUser, @PathVariable Long id) {

        return repository.findById(id)
                .map(x -> {
                    x.setValue(newUser.getValue());
                    return repository.save(x);
                })
                .orElseGet(() -> {
                    newUser.setId(id);
                    return repository.save(newUser);
                });
    }

    // update author only
    @PatchMapping("/{id}")
    User patch(@RequestBody Map<String, String> update, @PathVariable Long id) {

        return repository.findById(id)
                .map(x -> {
                    String value = update.get("value");
                    if (!StringUtils.isEmpty(value)) {
                        x.setValue(parseInt(value));

                        // better create a custom method to update a value = :newValue where id = :id
                        return repository.save(x);
                    } else {
                        throw new UsersUnSupportedFieldPatchException(update.keySet());
                    }

                })
                .orElseGet(() -> {
                    throw new UserNotFoundException(id);
                });

    }

    @DeleteMapping("/{id}")
    void deleteUser(@PathVariable Long id) {
        repository.deleteById(id);
    }


    private ArrayList<Integer> getListofUser(Long[] idList) throws NullPointerException {
        ArrayList<Integer> selectedUser = new ArrayList<>();

        if (idList != null) {
            for (Long id : idList) {
                User tmpUser = repository.findById(id).get();
                if (tmpUser != null)
                    if (tmpUser.getValue() != null)
                        selectedUser.add(tmpUser.getValue());
            }
        }
        if (selectedUser.size() == 0) {
            for (User user : repository.findAll()) {
                if (user.getValue() != null)
                    selectedUser.add(user.getValue());
            }
        }
        return selectedUser;
    }

    @PostMapping("/{action}")
    public Result action(@PathVariable String action, @RequestBody Long[] selectedId) {
        return new Result(actionMap.get(action).returnActionResult(getListofUser(selectedId)));
    }
}
