<?php

namespace App\Repository;

use App\data\SearchData;
use App\Entity\Produits;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\ORM\OptimisticLockException;
use Doctrine\ORM\ORMException;
use Doctrine\Persistence\ManagerRegistry;
use Knp\Component\Pager\Pagination\PaginationInterface;
use Knp\Component\Pager\PaginatorInterface;

/**
 * @method Produits|null find($id, $lockMode = null, $lockVersion = null)
 * @method Produits|null findOneBy(array $criteria, array $orderBy = null)
 * @method Produits[]    findAll()
 * @method Produits[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class ProduitsRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry, PaginatorInterface $paginator)
    {
        parent::__construct($registry, Produits::class);
        $this->paginator=$paginator;
    }

    /**
     * @throws ORMException
     * @throws OptimisticLockException
     */
    public function add(Produits $entity, bool $flush = true): void
    {
        $this->_em->persist($entity);
        if ($flush) {
            $this->_em->flush();
        }
    }

    /**
     * @throws ORMException
     * @throws OptimisticLockException
     */
    public function remove(Produits $entity, bool $flush = true): void
    {
        $this->_em->remove($entity);
        if ($flush) {
            $this->_em->flush();
        }
    }

      /**
     * Récupère les produits en lien avec une recherche
     * @return PaginationInterface
     */
    public function listProduitByCat($id,SearchData $search): PaginationInterface
    {
        $query= $this->createQueryBuilder('p')
            ->join('p.categorie', 'c')
            ->addSelect('c')
            ->andWhere('c.code=:id')
            ->setParameter('id', $id);
            if (!empty($search->q)) {
                $query = $query
                    ->andWhere('p.nom LIKE :q')
                    ->setParameter('q', "%{$search->q}%");
            }
    
            if (!empty($search->min)) {
                $query = $query
                    ->andWhere('p.prix >= :min')
                    ->setParameter('min', $search->min);
            }
    
            if (!empty($search->max)) {
                $query = $query
                    ->andWhere('p.prix <= :max')
                    ->setParameter('max', $search->max);
            }
    
            if (!empty($search->promo)) {
                $query = $query
                    ->andWhere('p.promo = 1');
            }
    
            if (!empty($search->categories)) {
                $query = $query
                    ->andWhere('c.code IN (:categories)')
                    ->setParameter('categories', $search->categories);
            }

            return $this->paginator->paginate(
                $query,
                $search->page,
                3
            );
           
           
    }
    public function findEntitiesByString($str)
    {
        return $this->getEntityManager()
            ->createQuery(
                'SELECT e
                FROM App\Entity\Produits e
                WHERE e.nom LIKE :str'
            )
            ->setParameter('str', '%'.$str.'%')
            ->getResult();
    }
    public function findEntitiesByRate($str)
    {
        return $this->getEntityManager()
            ->createQuery(
                'SELECT e
                FROM App\Entity\Produits e
                WHERE e.nom LIKE :str'
            )
            ->setParameter('str', '%'.$str.'%')
            ->getResult();
    }
    


    public function findEntitiesByRate2()
    {
        return $this->getEntityManager()
            ->createQuery(
                'SELECT e
                FROM App\Entity\Produits e
                WHERE e.rate >=4'
            )
            ->setMaxResults(6)
            ->getResult();
    }

    public function findEntitiesByRate3()
    {
        return $this->getEntityManager()
            ->createQuery(
                'SELECT p.nom  as nom ,p.rate  as rate  
            FROM App\Entity\Produits p
           '
            )
            ->getResult();
    }

    // /**
    //  * @return Produits[] Returns an array of Produits objects
    //  */
    /*
    public function findByExampleField($value)
    {
        return $this->createQueryBuilder('p')
            ->andWhere('p.exampleField = :val')
            ->setParameter('val', $value)
            ->orderBy('p.id', 'ASC')
            ->setMaxResults(10)
            ->getQuery()
            ->getResult()
        ;
    }
    */

    /*
    public function findOneBySomeField($value): ?Produits
    {
        return $this->createQueryBuilder('p')
            ->andWhere('p.exampleField = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getOneOrNullResult()
        ;
    }
    */
}
